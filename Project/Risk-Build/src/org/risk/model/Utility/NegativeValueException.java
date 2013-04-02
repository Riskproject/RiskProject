/*
 * Copyright (C) 2013 The Android Open Source Project
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

package org.risk.model.Utility;
/**
 * An instance of this class represents negative value exception.It should be thrown when value of a variable is negative.
 * @author Omer
 *
 */
public class NegativeValueException extends Exception {
	/**
	 * Default Constructor initialises exception with default message
	 */
	public NegativeValueException (){
		super("Negative Value not allowed!!");
	}
	/**
	 * Constructor initialises exception with specified message
	 * @param message	is a string variable which describes exception cause
	 */
	public NegativeValueException (String message){
		super(message);
	}

}
