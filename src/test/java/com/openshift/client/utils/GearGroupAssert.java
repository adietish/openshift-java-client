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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.fest.assertions.AssertExtension;

import com.openshift.client.GearState;
import com.openshift.client.IGear;
import com.openshift.client.IGearGroup;

/**
 * @author André Dietisheim
 */
public class GearGroupAssert implements AssertExtension {

	private IGearGroup gearGroup;

	public GearGroupAssert(IGearGroup gearGroup) {
		this.gearGroup = gearGroup;
	}

	public GearGroupAssert hasName(String name) {
		assertEquals(name, gearGroup.getName());
		return this;
	}

	public GearGroupAssert hasUUID(String uuid) {
		assertEquals(uuid, gearGroup.getUUID());
		return this;
	}

	public GearGroupAssert hasUUID() {
		assertNotNull(gearGroup.getUUID());
		return this;
	}

	public GearAssert containsGear(String id) {
		assertNotNull(id);

		for (IGear gear : gearGroup.getGears()) {
			if (id.equals(gear.getId())) {
				return new GearAssert(gear);
			}
		}
		fail("gear group " + gearGroup.getName() + " does not contain a gear with id " + id);
		return null;
	}

	public class GearAssert {

		private IGear gear;

		public GearAssert(IGear gear) {
			this.gear = gear;
		}
		
		public GearAssert hasId(String id) {
			assertNotNull(id);
			
			assertEquals(id, gear.getId());
			return this;
		}
		
		public GearGroupAssert inState(GearState state) {
			assertNotNull(state);
			
			assertEquals(state, gear.getState());
			return GearGroupAssert.this;
		}
		
	}
	
}
