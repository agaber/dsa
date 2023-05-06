/**
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and 
 * '0's (water), return the number of islands.
 * 
 * An island is surrounded by water and is formed by connecting adjacent lands 
 * horizontally or vertically. You may assume all four edges of the grid are all
 * surrounded by water.
 * 
 * https://leetcode.com/problems/number-of-islands
*/
export class NumIslands {
  numIslands(grid: string[][]): number {
    const height = grid.length;
    const width = grid[0].length;

    let islands = 0;
    for (let i = 0; i < height; i++) {
      for (let j = 0; j < width; j++) {
        if (grid[i][j] === '1') {
          islands++;
          this.explore(grid, i, j);
        }
      }
    }
    return islands;
  }

  private readonly directions = [[-1, 0], [0, 1], [1, 0], [0, -1]];

  private explore(grid: string[][], x: number, y: number) {
    // Visit.
    grid[x][y] = 'X';

    const height = grid.length;
    const width = grid[0].length;
    for (const delta of this.directions) {
      const xx = x + delta[0];
      const yy = y + delta[1];
      if (xx >= 0 && xx < height && yy >= 0 && yy < width && grid[xx][yy] === '1') {
        this.explore(grid, xx, yy);
      }
    }
  }
}

// LeetCode's usual definition of a graph node.
export class Node {
  val: number;
  neighbors: Node[];

  constructor(val?: number, neighbors?: Node[]) {
    this.val = (val === undefined ? 0 : val)
    this.neighbors = (neighbors === undefined ? [] : neighbors)
  }
}

/**
 * LeetCode tests often define a graph as an adjacency list, where the 1-based
 * array index implies the Edge value.
 */
export function createNodesFromAdjacencyList(adjList: number[][]): Node | undefined {
  if (!adjList.length) {
    return undefined;
  }

  const nodes: Node[] = Array(adjList.length);

  for (let i = 0; i < adjList.length; i++) {
    nodes[i] = new Node(i + 1);
  }

  for (let i = 0; i < adjList.length; i++) {
    for (let j = 0; j < adjList[i].length; j++) {
      nodes[i]!.neighbors.push(nodes[adjList[i][j] - 1]!);
    }
  }

  return nodes[0]!;
}

/**
 * Creates an adjacency list from a root node. This only works for a graph that
 * is fully connected and I'm only doing it so that I can test
 * createNodesFromAdjacencyList.
 * 
 * This is basically a variation of the clone graph question which I will solve
 * here next. I originaly did all this so I could test that specific solution
 * and hope that it can be used for future questions.
 * 
 * https://leetcode.com/problems/clone-graph
 */
export function createAdjacencyListFromNode(node?: Node): number[][] {
  if (!node) {
    return [];
  }

  const adjMap = new Map<number, number[]>();
  adjMap.set(node.val, []);

  const stack: Node[] = [];
  stack.push(node);

  while (stack.length) {
    const curr = stack.pop()!;
    for (const neighbor of curr.neighbors) {
      if (!adjMap.has(neighbor.val)) {
        adjMap.set(neighbor.val, []);
        stack.push(neighbor);
      }
      adjMap.get(curr.val)!.push(neighbor.val);
    }
  }

  const adjList: number[][] = Array(adjMap.size);
  for (let i = 0; i < adjMap.size; i++) {
    adjList[i] = adjMap.get(i + 1) || [];
  }
  return adjList;
};

/**
 * Given a reference of a node in a connected undirected graph.
 * 
 * Return a deep copy (clone) of the graph.
 * 
 * Each node in the graph contains a value (int) and a list (List[Node]) of its
 * neighbors.
 * 
 * - Time Complexity: O(N+M), where N is a number of nodes  (vertices) and M is 
 * the number of edges.
 * - Space Complexity: O(N). This space is occupied by the visited hash map and 
 * in addition to that, space would also be occupied by the recursion stack since
 * we are adopting a recursive approach here. The space occupied by the recursion
 * stack would be equal to O(H) where H is the height of the graph. Overall, the
 * space complexity would be O(N)O(N)O(N).
 * 
 * The solution is basically the recurisve version of createAdjacencyListFromNode.
 * The iterative solution comes more naturally to me but either versionis hard
 * tricky for me. For undirected graphs, try visiting while iterating over
 * neighbors.
 * 
 * https://leetcode.com/problems/clone-graph
 */
export function cloneGraph(node?: Node, visited = new Map<Node, Node>()): Node | undefined {
  if (!node) {
    return node;
  }

  if (visited.has(node)) {
    return visited.get(node);
  }

  visited.set(node, new Node(node.val));
  for (const neighbor of node.neighbors) {
    visited.get(node)!.neighbors.push(cloneGraph(neighbor, visited)!);
  }
  return visited.get(node);
};


export class PacificAtlanticWaterFlow {
  // Up, right, down, left.
  private static readonly DELTAS: [number, number][]
    = [[-1, 0], [0, 1], [1, 0], [0, -1]];

  private height = 0;
  private width = 0;
  private grid: number[][] = [];

  pacificAtlantic(heights: number[][]): number[][] {
    this.grid = heights;
    this.height = heights.length;
    this.width = heights[0].length;

    const atlantic = new Map<string, [number, number]>();
    const pacific = new Map<string, [number, number]>();

    // Explore the top and bottom rows.
    for (let col = 0; col < this.width; col++) {
      this.explore([0, col], pacific);
      this.explore([this.height - 1, col], atlantic);
    }

    // Explore the left and right columns.
    for (let row = 0; row < this.height; row++) {
      this.explore([row, 0], pacific);
      this.explore([row, this.width - 1], atlantic);
    }

    const merged: number[][] = [];
    for (const point of pacific.values()) {
      if (atlantic.has(this.toString(point))) {
        merged.push(point);
      }
    }
    return merged;
  }

  private explore(start: [number, number], visited: Map<string, [number, number]>) {
    if (!visited.has(this.toString(start))) {
      visited.set(this.toString(start), start);
      for (const delta of PacificAtlanticWaterFlow.DELTAS) {
        const next = this.move(start, delta);
        if (this.isValidMove(start, next)) {
          this.explore(next, visited);
        }
      }
    }
  }

  private move(point: [number, number], delta: [number, number]): [number, number] {
    return [point[0] + delta[0], point[1] + delta[1]];
  }

  private isInBounds(point: [number, number]): boolean {
    return point[0] >= 0 && point[0] < this.height
      && point[1] >= 0 && point[1] < this.width;
  }

  private isValidMove(current: [number, number], next: [number, number]): boolean {
    return this.isInBounds(next)
      && this.grid[current[0]][current[1]] <= this.grid[next[0]][next[1]];
  }

  private toString(point: [number, number]): string {
    return `${point[0]},${point[1]}`;
  }
}