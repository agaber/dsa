## Commands

### Run unit tests

```sh
$ npm test
```

### Compile

```sh
$ npm build
```

Alternatively `$ tsc` but I might change the TypeScript compiler.

### package.json

These commands are configured through the package.json file.

## Create a new Node.js project with TypeScript.

1. Install npm.
1. Install TypeScript

    ```sh
    $ npm install -g typescript
    ```

1. Initialize project dependencies.

    ```sh
    $ mkdir dsa
    $ cd dsa
    $ npm init 
    $ npm install --save-dev typescript # alternatively, just npm i -D 
    $ npm install --save-dev ts-node
    $ npm install --save-dev @types/node
    $ npm install --save-dev jasmine
    ```

1. You might have to do something else to set up Jasmine, like `npx init jasmine`. 
   Some links: 
    * https://devtails.xyz/@adam/how-to-run-unit-tests-with-jasmine-and-typescript
    * https://jasmine.github.io/setup/nodejs.html

1. Mess with config files (package.json, tsconfig.json, spec/support/jasmine.json).