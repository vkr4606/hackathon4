/*
 * Copyright (c) 2013, Sierra Wireless
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 *     * Redistributions of source code must retain the above copyright notice,
 *       this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright notice,
 *       this list of conditions and the following disclaimer in the documentation
 *       and/or other materials provided with the distribution.
 *     * Neither the name of {{ project }} nor the names of its contributors
 *       may be used to endorse or promote products derived from this software
 *       without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package leshan.tlv;

import static org.junit.Assert.assertEquals;

import java.nio.ByteBuffer;
import java.util.Date;

import org.junit.Test;

public class TlvEncoderTest {

    @Test
    public void encode_short() {
        byte[] encoded = TlvEncoder.encodeInteger(1234);

        // check value
        ByteBuffer bb = ByteBuffer.wrap(encoded);
        assertEquals(1234, bb.getShort());
        assertEquals(0, bb.remaining());
    }

    @Test
    public void encode_integer() {
        byte[] encoded = TlvEncoder.encodeInteger(1245823);

        // check value
        ByteBuffer bb = ByteBuffer.wrap(encoded);
        assertEquals(1245823, bb.getInt());
        assertEquals(0, bb.remaining());
    }

    @Test
    public void encode_long() {
        long value = System.currentTimeMillis();
        byte[] encoded = TlvEncoder.encodeInteger(value);

        // check value
        ByteBuffer bb = ByteBuffer.wrap(encoded);
        assertEquals(value, bb.getLong());
        assertEquals(0, bb.remaining());
    }

    @Test
    public void encode_date() {
        long timestamp = System.currentTimeMillis();
        byte[] encoded = TlvEncoder.encodeDate(new Date(timestamp));

        // check value
        ByteBuffer bb = ByteBuffer.wrap(encoded);
        assertEquals((int) (timestamp / 1000), bb.getInt());
        assertEquals(0, bb.remaining());
    }

    @Test
    public void encode_boolean() {
        byte[] encoded = TlvEncoder.encodeBoolean(true);

        // check value
        assertEquals(1, encoded.length);
        assertEquals(1, encoded[0]);
    }

}
