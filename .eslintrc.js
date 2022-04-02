// https://eslint.org/docs/user-guide/configuring

module.exports = {
  root: true,
  parserOptions: {
    parser: "babel-eslint"
  },
  env: {
    browser: true
  },
  extends: ["plugin:vue/essential"],
  plugins: ["vue"],
  rules: {
    // allow debugger during development
    "no-debugger": process.env.NODE_ENV === "production" ? "error" : "off",
    quotes: [2, "single", "avoid-escape"],
    "comma-dangle": [2, "always"], //对象字面量项尾不能有逗号
    "comma-dangle": ["error", {
      "arrays": "only-multiline",
      "objects": "only-multiline",
      "imports": "never",
      "exports": "always",
      "functions": "never"
  }]
  }
};
