/**
 * @param {Function[]} functions
 * @return {Function}
 */
var compose = function(functions) {
    return function(x) {
        let val = x;
        for (let i = functions.length - 1; i >= 0; i--) {
            val = functions[i](val);
        }
        return val;
    };
};