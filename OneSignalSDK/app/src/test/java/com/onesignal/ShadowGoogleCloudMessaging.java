/**
 * Modified MIT License
 *
 * Copyright 2015 OneSignal
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * 1. The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * 2. All copies of substantial portions of the Software may only be used in connection
 * with services provided by OneSignal.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.onesignal;

import android.content.Context;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;
import org.robolectric.annotation.RealObject;
import org.robolectric.internal.ShadowExtractor;

import static org.robolectric.Shadows.shadowOf;

@Implements(GoogleCloudMessaging.class)
public class ShadowGoogleCloudMessaging {
   public static boolean exists = true;

   @Implementation
   public static synchronized GoogleCloudMessaging getInstance(Context context) throws ClassNotFoundException {
      if (!exists)
         throw new ClassNotFoundException();

      // This does not quite do what the real GoogleCloudMessaging.getInstance(Context) does but seems close enought.
      return new GoogleCloudMessaging();

      // @RealObject does not work with static methods, even if I create an instance with new then get it from the object.
      // Last thing to try if this is needed is to use reflection.
      //GoogleCloudMessaging gcm = new GoogleCloudMessaging();
      //ShadowGoogleCloudMessaging shadowOfGcm = (ShadowGoogleCloudMessaging)ShadowExtractor.extract(gcm);

      //return shadowOfGcm.realInstance.getInstance(context);
      //return com.google.android.gms.gcm.GoogleCloudMessaging.getInstance(context);
      //return GoogleCloudMessaging.getInstance(context);
   }
}
