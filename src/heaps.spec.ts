import * as h from './heaps';

describe('Best Time to Buy and Sell Stock', () => {
  it('should return the best time to buy and sell stock', () => {
    expect(h.lastStoneWeight([2, 7, 4, 1, 8, 1])).toEqual(1);
    expect(h.lastStoneWeight([1])).toEqual(1);
    expect(h.lastStoneWeight([2, 2])).toEqual(0);
  });
});