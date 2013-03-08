/******************************************************************************* 
 * Copyright (c) 2007 Red Hat, Inc. 
 * Distributed under license by Red Hat, Inc. All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 ******************************************************************************/
package com.openshift.client.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.fest.assertions.AssertExtension;

import com.openshift.client.IGearGroup;
import com.openshift.internal.client.utils.StringUtils;

/**
 * @author André Dietisheim
 */
public class GearGroupsAssert implements AssertExtension {

	private Collection<IGearGroup> gearGroups;

	public GearGroupsAssert(Collection<IGearGroup> gearGroups) {
		this.gearGroups = gearGroups;
	}

	public GearGroupsAssert contains(String name) {
		assertFalse(StringUtils.isEmpty(name));
		assertNotNull("Asserted GearGroups didn't contain group with name " + name, getByName(name));
		return this;
	}
	
	public GearGroupAssert assertGroup(String name) {
		assertFalse(StringUtils.isEmpty(name));
		contains(name);
		return new GearGroupAssert(getByName(name));
	}

	private IGearGroup getByName(String name) {
		for (IGearGroup gearGroup : gearGroups) {
			if (name.equals(gearGroup.getName())) {
				return gearGroup;
			}
		}
		return null;
	}

}
