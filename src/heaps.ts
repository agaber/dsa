import { MaxHeap } from "datastructures-js";

/**
 * Last Stone Weight
 * 
 * You are given an array of integers stones where stones[i] is the weight of 
 * the ith stone.
 * 
 * We are playing a game with the stones. On each turn, we choose the heaviest 
 * two stones and smash them together. Suppose the heaviest two stones have
 * weights x and y with x <= y. The result of this smash is:
 * 
 * - If x == y, both stones are destroyed, and
 * - If x != y, the stone of weight x is destroyed, and the stone of weight y 
 *   has new weight y - x.
 * - At the end of the game, there is at most one stone left.
 * 
 * Return the weight of the last remaining stone. If there are no stones left,
 * return 0.
 * 
 * https://leetcode.com/problems/last-stone-weight
 */
export function lastStoneWeight(stones: number[]): number {
  const heap = MaxHeap.heapify(stones);

  while (heap.size() > 1) {
    const y = heap.pop();
    const x = heap.pop();
    console.log(x, y);
    if (x !== y) {
      heap.push(y - x);
    }
  }

  return heap.isEmpty() ? 0 : heap.pop();
};