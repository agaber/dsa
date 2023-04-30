import { sum, toBinary } from "./fun";

describe("sum", () => {
  it("should add two numbers", () => {
    expect(sum(1, 2)).toBe(3);
  });
});

describe("toBinary", () => {
  it('should convert number to binary string', () => {
    expect(toBinary(5)).toEqual('00000101');
  });
});