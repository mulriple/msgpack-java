//
// MessagePack for Java
//
// Copyright (C) 2009-2011 FURUHASHI Sadayuki
//
//    Licensed under the Apache License, Version 2.0 (the "License");
//    you may not use this file except in compliance with the License.
//    You may obtain a copy of the License at
//
//        http://www.apache.org/licenses/LICENSE-2.0
//
//    Unless required by applicable law or agreed to in writing, software
//    distributed under the License is distributed on an "AS IS" BASIS,
//    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//    See the License for the specific language governing permissions and
//    limitations under the License.
//
package org.msgpack.unpacker;

import java.math.BigInteger;
import org.msgpack.MessageTypeException;

final class BigIntegerAccept extends Accept {
    BigInteger value;

    @Override
    void acceptInteger(byte v) {
        this.value = BigInteger.valueOf((long)v);
    }

    @Override
    void acceptInteger(short v) {
        this.value = BigInteger.valueOf((long)v);
    }

    @Override
    void acceptInteger(int v) {
        this.value = BigInteger.valueOf((long)v);
    }

    @Override
    void acceptInteger(long v) {
        this.value = BigInteger.valueOf(v);
    }

    @Override
    void acceptUnsignedInteger(byte v) {
        BigInteger.valueOf((long)(v & 0xff));
    }

    @Override
    void acceptUnsignedInteger(short v) {
        BigInteger.valueOf((long)(v & 0xffff));
    }

    @Override
    void acceptUnsignedInteger(int v) {
        if(v < 0) {
            // FIXME
            this.value = BigInteger.valueOf(0xffffffffffffffffL + v + 1L);
        } else {
            this.value = BigInteger.valueOf((long)v);
        }
    }

    @Override
    void acceptUnsignedInteger(long v) {
        if(v < 0) {
            this.value = BigInteger.valueOf(v+Long.MAX_VALUE+1).setBit(63);
        } else {
            this.value = BigInteger.valueOf(v);
        }
    }
}

