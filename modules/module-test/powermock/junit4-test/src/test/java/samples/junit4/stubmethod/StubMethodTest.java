/*
 * Copyright 2009 the original author or authors.
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
package samples.junit4.stubmethod;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.easymock.PowerMock.stubMethod;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import org.powermock.reflect.exceptions.MethodNotFoundException;
import org.powermock.reflect.exceptions.TooManyMethodsFoundException;

import samples.suppressmethod.SuppressMethod;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SuppressMethod.class)
public class StubMethodTest {

    @Test
    public void whenStubbingInstanceMethodTheMethodReturnsTheStubbedValue() throws Exception {
        String expectedValue = "Hello";
        stubMethod(SuppressMethod.class, "getObject", expectedValue);

        SuppressMethod tested = new SuppressMethod();

        assertEquals(expectedValue, tested.getObject());
        assertEquals(expectedValue, tested.getObject());
    }

    @Test
    public void whenStubbingStaticMethodTheMethodReturnsTheStubbedValue() throws Exception {
        String expectedValue = "Hello";
        stubMethod(SuppressMethod.class, "getObjectStatic", expectedValue);

        assertEquals(expectedValue, SuppressMethod.getObjectStatic());
        assertEquals(expectedValue, SuppressMethod.getObjectStatic());
    }

    @Test
    public void whenStubbingInstanceMethodWithPrimiteValueTheMethodReturnsTheStubbedValue() throws Exception {
        float expectedValue = 4;
        stubMethod(SuppressMethod.class, "getFloat", expectedValue);

        SuppressMethod tested = new SuppressMethod();

        assertEquals(expectedValue, tested.getFloat(), 0.0f);
        assertEquals(expectedValue, tested.getFloat(), 0.0f);
    }

    @Test(expected = TooManyMethodsFoundException.class)
    public void whenSeveralMethodsFoundThenTooManyMethodsFoundExceptionIsThrown() throws Exception {
        stubMethod(SuppressMethod.class, "sameName", null);
    }

    @Test(expected = MethodNotFoundException.class)
    public void whenNoMethodsFoundThenMethodNotFoundExceptionIsThrown() throws Exception {
        stubMethod(SuppressMethod.class, "notFound", null);
    }

    @Test
    public void whenStubbingInstanceMethodByPassingTheMethodTheMethodReturnsTheStubbedValue() throws Exception {
        String expected = "Hello";
        stubMethod(Whitebox.getMethod(SuppressMethod.class, "getObject"), expected);

        SuppressMethod tested = new SuppressMethod();

        assertEquals(expected, tested.getObject());
        assertEquals(expected, tested.getObject());
    }

    @Test
    public void whenStubbingStaticMethodByPassingTheMethodTheMethodReturnsTheStubbedValue() throws Exception {
        String expected = "Hello";
        stubMethod(Whitebox.getMethod(SuppressMethod.class, "getObjectStatic"), expected);

        assertEquals(expected, SuppressMethod.getObjectStatic());
        assertEquals(expected, SuppressMethod.getObjectStatic());
    }

    @Test(expected = ClassCastException.class)
    public void whenStubbingInstanceMethodWithWrongReturnTypeThenClasscastExceptionIsThrown() throws Exception {
        String illegalReturnType = "Hello";
        stubMethod(Whitebox.getMethod(SuppressMethod.class, "getFloat"), illegalReturnType);
        SuppressMethod tested = new SuppressMethod();
        tested.getFloat();

    }
}