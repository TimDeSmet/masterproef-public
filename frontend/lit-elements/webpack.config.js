const path = require("path");

module.exports = {
    entry: "./index.js",
    target: 'node',
    output: {
        filename: "main.js",
        path: path.resolve(__dirname, "dist"),
        publicPath: "dist/"
    },
    module: {
        rules: [
            {
                test: /\.css$/,
                // css-loader: interprets our import statement for the css
                // style-loader: injects our css to the DOM
                use: ["style-loader", "css-loader"]
            },
            {
                test: /\.(jpg|png|svg)$/,
                // url-loader: transforms files into base64
                loader: "url-loader",
                options: {
                    name: "[name].[ext]",
                    outputPath: "images",
                    limit: Infinity 
                }
            }
        ]
    }
};