import * as g from './graphs';

describe('Number of Islands', () => {
  it('should pass test case 1', () => {
    const grid = [
      ['1', '1', '1', '1', '0'],
      ['1', '1', '0', '1', '0'],
      ['1', '1', '0', '0', '0'],
      ['0', '0', '0', '0', '0'],
    ];
    const solution = new g.NumIslands();
    expect(solution.numIslands(grid)).toEqual(1);
  });

  it('should pass test case 2', () => {
    const grid = [
      ['1', '1', '0', '0', '0'],
      ['1', '1', '0', '0', '0'],
      ['0', '0', '1', '0', '0'],
      ['0', '0', '0', '1', '1'],
    ];
    const solution = new g.NumIslands();
    expect(solution.numIslands(grid)).toEqual(3);
  });
});

describe('Create graphs to/from adjacency lists', () => {
  it('should create graph nodes and adjacency lists', () => {
    const adjList = [[2, 4], [1, 3], [2, 4], [1, 3]];
    const node = g.createNodesFromAdjacencyList(adjList);
    expect(g.createAdjacencyListFromNode(node)).toEqual(adjList);
  });
});

describe('Clone graph', () => {
  it('should create graph nodes and adjacency lists', () => {
    const adjList = [[2, 4], [1, 3], [2, 4], [1, 3]];
    const node = g.createNodesFromAdjacencyList(adjList);
    expect(g.cloneGraph(node)).toEqual(node);
  });
});


describe('Pacific Atlantic Water Flow', () => {
  it('should should pass test case 1', () => {
    const grid = [
      [1, 2, 2, 3, 5],
      [3, 2, 3, 4, 4],
      [2, 4, 5, 3, 1],
      [6, 7, 1, 4, 5],
      [5, 1, 1, 2, 4],
    ];
    const actual = new g.PacificAtlanticWaterFlow().pacificAtlantic(grid);
    expect(actual).toEqual([[0, 4], [1, 3], [1, 4], [2, 2], [3, 1], [3, 0], [4, 0]]);
  });

  it('should should pass test case 2', () => {
    const grid = [[1]];
    const actual = new g.PacificAtlanticWaterFlow().pacificAtlantic(grid);
    expect(actual).toEqual([[0, 0]]);
  });
});

describe('Course Schedule', () => {
  const parameters = [
    { impl: new g.CourseScheduleIterative(), type: 'iterative' },
    { impl: new g.CourseScheduleRecurisve(), type: 'recursive' },
  ];

  parameters.forEach((param) => {
    it(`should pass test case 1 with ${param.type} algorithm`, () => {
      expect(param.impl.canFinish(2, [[1, 0]])).toBeTrue();
    });

    it(`should pass test case 2 with ${param.type} algorithm`, () => {
      expect(param.impl.canFinish(2, [[1, 0], [0, 1]])).toBeFalse();
    });

    it(`should pass test case 3 with ${param.type} algorithm`, () => {
      expect(param.impl.canFinish(6, [[1, 4], [2, 4], [3, 1], [3, 2]]))
        .toBeTrue();
    });

    it(`should pass test case 4 with ${param.type} algorithm`, () => {
      expect(param.impl.canFinish(20,
        [[0, 10], [3, 18], [5, 5], [6, 11], [11, 14], [13, 1], [15, 1], [17, 4]]))
        .toBeFalse();
    });

    it(`should pass test case 5 with ${param.type} algorithm`, () => {
      expect(param.impl.canFinish(4, [[0, 1], [1, 2], [0, 3], [3, 0]]))
        .toBeFalse();
    });
  });
});