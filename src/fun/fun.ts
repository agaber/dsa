export function sum(num1: number, num2: number): number {
  return num1 + num2;
}

export function toBinary(number: number, base: number = 8): string {
  let binary: string = '';
  for (let i = 0; i < base; i++) {
    binary = String(number % 2) + binary;
    number = number >> 1;
  }
  return binary;
}