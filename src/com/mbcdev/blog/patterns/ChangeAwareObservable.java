package com.mbcdev.blog.patterns;

/*
 * Copyright (c) 1994, 2004, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represents an observable object, or "data"
 * in the model-view paradigm. It can be subclassed to represent an
 * object that the application wants to have observed.
 * <p>
 * An observable object can have one or more observers. An observer
 * may be any object that implements interface <tt>com.mbcdev.blog.patterns.Observer</tt>. After an
 * observable instance changes, an application calling the
 * <code>Observable</code>'s <code>notifyObservers</code> method
 * causes all of its observers to be notified of the change by a call
 * to their <code>update</code> method.
 * <p>
 * The order in which notifications will be delivered is unspecified.
 * The default implementation provided in the Observable class will
 * notify Observers in the order in which they registered interest, but
 * subclasses may change this order, use no guaranteed order, deliver
 * notifications on separate threads, or may guarantee that their
 * subclass follows this order, as they choose.
 * <p>
 * Note that this notification mechanism is has nothing to do with threads
 * and is completely separate from the <tt>wait</tt> and <tt>notify</tt>
 * mechanism of class <tt>Object</tt>.
 * <p>
 * When an observable object is newly created, its set of observers is
 * empty. Two observers are considered the same if and only if the
 * <tt>equals</tt> method returns true for them.
 *
 * This class has been modified from the original by mbcdev.com to do a
 * number of things like remove the use of the deprecated Vector class
 * and to extract an interface for Observer and provide this as a default
 * concrete class.
 *
 * @author  Chris Warth
 * @see     java.util.Observable#notifyObservers()
 * @see     java.util.Observable#notifyObservers(java.lang.Object)
 * @see     java.util.Observer
 * @see     java.util.Observer#update(java.util.Observable, java.lang.Object)
 * @since   JDK1.0
 */
public class ChangeAwareObservable implements Observable {
    private boolean changed = false;
    private List<Observer> observers;

    /** Construct an Observable with zero Observers. */

    public ChangeAwareObservable() {
        observers = Collections.synchronizedList(new ArrayList<Observer>());
    }

    @Override
    public synchronized void addObserver(Observer o) {
        if (o == null) {
            throw new NullPointerException();
        }

        if (!observers.contains(o)) {
            observers.add(o);
        }
    }

    @Override
    public synchronized void deleteObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        notifyObservers(null);
    }

    @Override
    public void notifyObservers(Object arg) {
        /*
         * a temporary array buffer, used as a snapshot of the state of
         * current Observers.
         */
        List<Observer> observersLocal;

        synchronized (this) {
            /* We don't want the com.mbcdev.blog.patterns.Observer doing callbacks into
             * arbitrary code while holding its own Monitor.
             * The code where we extract each Observable from
             * the Vector and store the state of the com.mbcdev.blog.patterns.Observer
             * needs synchronization, but notifying observers
             * does not (should not).  The worst result of any
             * potential race-condition here is that:
             * 1) a newly-added com.mbcdev.blog.patterns.Observer will miss a
             *   notification in progress
             * 2) a recently unregistered com.mbcdev.blog.patterns.Observer will be
             *   wrongly notified when it doesn't care
             */
            if (!changed) {
                return;
            }

            observersLocal = Collections.unmodifiableList(observers);
            clearChanged();
        }

        for (Observer observerLocal: observersLocal) {
            //TODO unchecked call to raw type
            observerLocal.update(this, arg);
        }
    }

    @Override
    public synchronized void deleteObservers() {
        observers.clear();
    }

    /**
     * Marks this <tt>Observable</tt> object as having been changed; the
     * <tt>hasChanged</tt> method will now return <tt>true</tt>.
     */
    protected synchronized void setChanged() {
        changed = true;
    }

    /**
     * Indicates that this object has no longer changed, or that it has
     * already notified all of its observers of its most recent change,
     * so that the <tt>hasChanged</tt> method will now return <tt>false</tt>.
     * This method is called automatically by the
     * <code>notifyObservers</code> methods.
     *
     * @see     java.util.Observable#notifyObservers()
     * @see     java.util.Observable#notifyObservers(java.lang.Object)
     */
    protected synchronized void clearChanged() {
        changed = false;
    }

    /**
     * Tests if this object has changed.
     *
     * @return  <code>true</code> if and only if the <code>setChanged</code>
     *          method has been called more recently than the
     *          <code>clearChanged</code> method on this object;
     *          <code>false</code> otherwise.
     * @see     java.util.Observable#clearChanged()
     * @see     java.util.Observable#setChanged()
     */
    public synchronized boolean hasChanged() {
        return changed;
    }

    /**
     * Returns the number of observers of this <tt>Observable</tt> object.
     *
     * @return  the number of observers of this object.
     */
    public synchronized int countObservers() {
        return observers.size();
    }
}
