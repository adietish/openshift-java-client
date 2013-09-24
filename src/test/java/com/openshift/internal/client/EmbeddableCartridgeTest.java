/******************************************************************************* 
 * Copyright (c) 2013 Red Hat, Inc. 
 * Distributed under license by Red Hat, Inc. All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 ******************************************************************************/
package com.openshift.internal.client;

import static org.fest.assertions.Assertions.assertThat;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

import com.openshift.client.cartridge.EmbeddableCartridge;
import com.openshift.client.cartridge.StandaloneCartridge;
import com.openshift.client.utils.Cartridges;

/**
 * @author Andre Dietisheim
 */
public class EmbeddableCartridgeTest {

	@Test
	public void shouldNonDownloadableEqualsNonDownloadable() {
		// pre-coniditions
		// operation
		// verification
		assertThat(new EmbeddableCartridge("redhat"))
				.isEqualTo(new EmbeddableCartridge("redhat"));
		assertThat(new EmbeddableCartridge("redhat"))
				.isNotEqualTo(new EmbeddableCartridge("jboss"));
	}

	@Test
	public void shouldDownloadableEqualsDownloadable() throws MalformedURLException {
		// pre-coniditions
		// operation
		// verification
		assertThat(new EmbeddableCartridge(new URL(Cartridges.FOREMAN_DOWNLOAD_URL)))
				.isEqualTo(new EmbeddableCartridge(new URL(Cartridges.FOREMAN_DOWNLOAD_URL)));
	}

	@Test
	public void shouldDownloadableWithNonEqualNameEqualsDownloadable() throws MalformedURLException {
		// pre-coniditions
		// operation
		// verification
		assertThat(new EmbeddableCartridge("redhat", new URL(Cartridges.FOREMAN_DOWNLOAD_URL)))
				.isEqualTo(new EmbeddableCartridge(null, new URL(Cartridges.FOREMAN_DOWNLOAD_URL)));
		// should equal if url is equal, name doesnt matter 
		// (name is updated as soon as cartridge is deployed)
		assertThat(new EmbeddableCartridge("jboss", new URL(Cartridges.FOREMAN_DOWNLOAD_URL)))
				.isEqualTo(new EmbeddableCartridge("redhat", new URL(Cartridges.FOREMAN_DOWNLOAD_URL)));
	}

	@Test
	public void shouldDownloadableStandaloneNotEqualsDownloadableEmbeddable() throws MalformedURLException {
		// pre-coniditions
		// operation
		// verification
		assertThat(new EmbeddableCartridge(null, new URL(Cartridges.FOREMAN_DOWNLOAD_URL)))
				.isNotEqualTo(new StandaloneCartridge(null, new URL(Cartridges.GO_DOWNLOAD_URL)));
	}

}