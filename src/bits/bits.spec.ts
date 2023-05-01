//import { sum, toBinaryString } from '@/bits/bits';
import * as bits from './bits';

describe("sum", () => {
  it("should add two numbers", () => {
    expect(bits.sum(1, 2)).toBe(3);
  });
});

describe("toBinaryString", () => {
  it('should convert number to binary string', () => {
    expect(bits.toBinaryString(5, 8)).toEqual('0000 0101');
    expect(bits.toBinaryString(44, 6)).toEqual('10 1100');
    expect(bits.toBinaryString(10, 16)).toEqual('0000 0000 0000 1010');
    expect(bits.toBinaryString(50))
      .toEqual('0000 0000 0000 0000 0000 0000 0011 0010');
  });

  it('should convert number to binary string without spaces', () => {
    expect(bits.toBinaryString(5, 8, false)).toEqual('00000101');
    expect(bits.toBinaryString(44, 6, false)).toEqual('101100');
    expect(bits.toBinaryString(10, 8, false)).toEqual('00001010');
    expect(bits.toBinaryString(10, 16, false)).toEqual('0000000000001010');
    expect(bits.toBinaryString(50, 32, false))
      .toEqual('00000000000000000000000000110010');
  });

  it('should convert negative numbers to binary string', () => {
    expect(bits.toBinaryString(-10, 8, true)).toEqual('1111 0110');
  });
});

describe("fromBinaryString", () => {
  it('should convert a binary strings to numbers', () => {
    expect(bits.fromBinaryString('0000 0101')).toEqual(5);
    expect(bits.fromBinaryString('010 1100')).toEqual(44);
    expect(bits.fromBinaryString('0000 0000 0000 1010')).toEqual(10);
    expect(bits.fromBinaryString('0000 0000 0000 0000 0000 0000 0011 0010'))
      .toEqual(50);
    expect(bits.fromBinaryString('00000000000000000000000000110010'))
      .toEqual(50);
  });

  it('should convert binary strings to negative numbers', () => {
    expect(bits.fromBinaryString('111011')).toEqual(-5);
    expect(bits.fromBinaryString('1111 1111 1111 1111 1111 1111 1100 1110'))
      .toEqual(-50);
    expect(bits.fromBinaryString('11111111111111111111111111001110'))
      .toEqual(-50);
  });

  it('should throw an error for invalid input', () => {
    expect(() => bits.fromBinaryString('111 abc 1111'))
      .toThrow(new Error('invalid binary string'));
  });
});

describe("hammingWeight", () => {
  it('should calculate hammingWeight', () => {
    const number1 = bits.fromBinaryString('00000000000000000000000000001011');
    expect(bits.hammingWeight(number1)).toEqual(3);

    const number2 = bits.fromBinaryString('00000000000000000000000010000000');
    expect(bits.hammingWeight(number2)).toEqual(1);

    const number3 = bits.fromBinaryString('11111111111111111111111111111101');
    expect(bits.hammingWeight(number3)).toEqual(31);
  });
});