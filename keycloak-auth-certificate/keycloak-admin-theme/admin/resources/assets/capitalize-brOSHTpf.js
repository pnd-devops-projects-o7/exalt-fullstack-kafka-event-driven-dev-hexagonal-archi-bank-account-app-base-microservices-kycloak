import{ev as n}from"./main-C047jAp3.js";import{b as R}from"./_baseSlice-F8doVSIJ.js";function g(r,a,e){var o=r.length;return e=e===void 0?o:e,!a&&e>=o?r:R(r,a,e)}var p="\\ud800-\\udfff",v="\\u0300-\\u036f",C="\\ufe20-\\ufe2f",S="\\u20d0-\\u20ff",A=v+C+S,$="\\ufe0e\\ufe0f",h="\\u200d",M=RegExp("["+h+p+A+$+"]");function s(r){return M.test(r)}function y(r){return r.split("")}var t="\\ud800-\\udfff",k="\\u0300-\\u036f",U="\\ufe20-\\ufe2f",j="\\u20d0-\\u20ff",x=k+U+j,F="\\ufe0e\\ufe0f",H="["+t+"]",u="["+x+"]",f="\\ud83c[\\udffb-\\udfff]",J="(?:"+u+"|"+f+")",i="[^"+t+"]",c="(?:\\ud83c[\\udde6-\\uddff]){2}",d="[\\ud800-\\udbff][\\udc00-\\udfff]",O="\\u200d",l=J+"?",m="["+F+"]?",T="(?:"+O+"(?:"+[i,c,d].join("|")+")"+m+l+")*",V=m+l+T,z="(?:"+[i+u+"?",u,c,d,H].join("|")+")",E=RegExp(f+"(?="+f+")|"+z+V,"g");function W(r){return r.match(E)||[]}function Z(r){return s(r)?W(r):y(r)}function q(r){return function(a){a=n(a);var e=s(a)?Z(a):void 0,o=e?e[0]:a.charAt(0),b=e?g(e,1).join(""):a.slice(1);return o[r]()+b}}var w=q("toUpperCase");function P(r){return w(n(r).toLowerCase())}export{P as c};
//# sourceMappingURL=capitalize-brOSHTpf.js.map
