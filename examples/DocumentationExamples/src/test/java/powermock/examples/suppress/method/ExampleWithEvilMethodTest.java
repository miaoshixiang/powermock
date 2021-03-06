/*
 * Copyright 2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package powermock.examples.suppress.method;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.support.membermodification.MemberMatcher.method;
import static org.powermock.api.support.membermodification.MemberModifier.suppress;

/**
 * This test demonstrates PowerMock's ability to suppress methods.
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ExampleWithEvilMethod.class)
public class ExampleWithEvilMethodTest {

	@Test
	public void testSuppressMethod() throws Exception {
		suppress(method(ExampleWithEvilMethod.class, "getEvilMessage"));
		final String message = "myMessage";
		ExampleWithEvilMethod tested = new ExampleWithEvilMethod(message);
		assertEquals(message + "null", tested.getMessage());
	}
}
