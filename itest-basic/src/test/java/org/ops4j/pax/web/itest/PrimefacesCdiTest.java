/*
 * Copyright 2014 Harald Wellmann.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ops4j.pax.web.itest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.ops4j.pax.exam.CoreOptions.junitBundles;
import static org.ops4j.pax.exam.CoreOptions.linkBundle;
import static org.ops4j.pax.exam.CoreOptions.mavenBundle;
import static org.ops4j.pax.exam.CoreOptions.options;
import static org.ops4j.pax.web.itest.util.TestConfiguration.logbackBundles;
import static org.ops4j.pax.web.itest.util.TestConfiguration.mojarraBundles;
import static org.ops4j.pax.web.itest.util.TestConfiguration.paxCdiSharedBundles;
import static org.ops4j.pax.web.itest.util.TestConfiguration.paxCdiWithWeldBundles;
import static org.ops4j.pax.web.itest.util.TestConfiguration.paxUndertowBundles;
import static org.ops4j.pax.web.itest.util.TestConfiguration.undertowBundles;
import static org.ops4j.pax.web.itest.util.WebAssertions.assertResourceContainsString;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;


@RunWith(PaxExam.class)
public class PrimefacesCdiTest {

    @Inject
    private ServletContext servletContext;
    

    @Configuration
    public Option[] config() {

        return options(
            linkBundle("pax-web-sample-primefaces-cdi"),
            mavenBundle("org.primefaces", "primefaces", "5.0"),

            undertowBundles(),
            paxUndertowBundles(),
            mojarraBundles(),            
            paxCdiSharedBundles(),
            paxCdiWithWeldBundles(),
            logbackBundles(),
            junitBundles());
    }
    
    @Test
    public void runPrimefacesCdiFacelet() throws Exception {
        assertThat(servletContext.getContextPath(), is("/primefaces-cdi"));
        assertResourceContainsString("primefaces-cdi/poll.jsf", "Felix");
    }
}
