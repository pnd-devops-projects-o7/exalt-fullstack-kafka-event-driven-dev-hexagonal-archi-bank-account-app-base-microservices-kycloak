import{c as L}from"./capitalize-brOSHTpf.js";import{ev as s}from"./main-C047jAp3.js";function E(u,e,r,g0){for(var f=-1,U=u==null?0:u.length;++f<U;)r=e(r,u[f],f,u);return r}function h(u){return function(e){return u?.[e]}}var z={À:"A",Á:"A",Â:"A",Ã:"A",Ä:"A",Å:"A",à:"a",á:"a",â:"a",ã:"a",ä:"a",å:"a",Ç:"C",ç:"c",Ð:"D",ð:"d",È:"E",É:"E",Ê:"E",Ë:"E",è:"e",é:"e",ê:"e",ë:"e",Ì:"I",Í:"I",Î:"I",Ï:"I",ì:"i",í:"i",î:"i",ï:"i",Ñ:"N",ñ:"n",Ò:"O",Ó:"O",Ô:"O",Õ:"O",Ö:"O",Ø:"O",ò:"o",ó:"o",ô:"o",õ:"o",ö:"o",ø:"o",Ù:"U",Ú:"U",Û:"U",Ü:"U",ù:"u",ú:"u",û:"u",ü:"u",Ý:"Y",ý:"y",ÿ:"y",Æ:"Ae",æ:"ae",Þ:"Th",þ:"th",ß:"ss",Ā:"A",Ă:"A",Ą:"A",ā:"a",ă:"a",ą:"a",Ć:"C",Ĉ:"C",Ċ:"C",Č:"C",ć:"c",ĉ:"c",ċ:"c",č:"c",Ď:"D",Đ:"D",ď:"d",đ:"d",Ē:"E",Ĕ:"E",Ė:"E",Ę:"E",Ě:"E",ē:"e",ĕ:"e",ė:"e",ę:"e",ě:"e",Ĝ:"G",Ğ:"G",Ġ:"G",Ģ:"G",ĝ:"g",ğ:"g",ġ:"g",ģ:"g",Ĥ:"H",Ħ:"H",ĥ:"h",ħ:"h",Ĩ:"I",Ī:"I",Ĭ:"I",Į:"I",İ:"I",ĩ:"i",ī:"i",ĭ:"i",į:"i",ı:"i",Ĵ:"J",ĵ:"j",Ķ:"K",ķ:"k",ĸ:"k",Ĺ:"L",Ļ:"L",Ľ:"L",Ŀ:"L",Ł:"L",ĺ:"l",ļ:"l",ľ:"l",ŀ:"l",ł:"l",Ń:"N",Ņ:"N",Ň:"N",Ŋ:"N",ń:"n",ņ:"n",ň:"n",ŋ:"n",Ō:"O",Ŏ:"O",Ő:"O",ō:"o",ŏ:"o",ő:"o",Ŕ:"R",Ŗ:"R",Ř:"R",ŕ:"r",ŗ:"r",ř:"r",Ś:"S",Ŝ:"S",Ş:"S",Š:"S",ś:"s",ŝ:"s",ş:"s",š:"s",Ţ:"T",Ť:"T",Ŧ:"T",ţ:"t",ť:"t",ŧ:"t",Ũ:"U",Ū:"U",Ŭ:"U",Ů:"U",Ű:"U",Ų:"U",ũ:"u",ū:"u",ŭ:"u",ů:"u",ű:"u",ų:"u",Ŵ:"W",ŵ:"w",Ŷ:"Y",ŷ:"y",Ÿ:"Y",Ź:"Z",Ż:"Z",Ž:"Z",ź:"z",ż:"z",ž:"z",Ĳ:"IJ",ĳ:"ij",Œ:"Oe",œ:"oe",ŉ:"'n",ſ:"s"},M=h(z),S=/[\xc0-\xd6\xd8-\xf6\xf8-\xff\u0100-\u017f]/g,Z="\\u0300-\\u036f",I="\\ufe20-\\ufe2f",k="\\u20d0-\\u20ff",D=Z+I+k,N="["+D+"]",W=RegExp(N,"g");function $(u){return u=s(u),u&&u.replace(S,M).replace(W,"")}var j=/[^\x00-\x2f\x3a-\x40\x5b-\x60\x7b-\x7f]+/g;function T(u){return u.match(j)||[]}var w=/[a-z][A-Z]|[A-Z]{2}[a-z]|[0-9][a-zA-Z]|[a-zA-Z][0-9]|[^a-zA-Z0-9 ]/;function H(u){return w.test(u)}var c="\\ud800-\\udfff",y="\\u0300-\\u036f",G="\\ufe20-\\ufe2f",J="\\u20d0-\\u20ff",P=y+G+J,i="\\u2700-\\u27bf",t="a-z\\xdf-\\xf6\\xf8-\\xff",V="\\xac\\xb1\\xd7\\xf7",Y="\\x00-\\x2f\\x3a-\\x40\\x5b-\\x60\\x7b-\\xbf",B="\\u2000-\\u206f",_=" \\t\\x0b\\f\\xa0\\ufeff\\n\\r\\u2028\\u2029\\u1680\\u180e\\u2000\\u2001\\u2002\\u2003\\u2004\\u2005\\u2006\\u2007\\u2008\\u2009\\u200a\\u202f\\u205f\\u3000",b="A-Z\\xc0-\\xd6\\xd8-\\xde",q="\\ufe0e\\ufe0f",g=V+Y+B+_,p="['’]",a="["+g+"]",F="["+P+"]",R="\\d+",K="["+i+"]",l="["+t+"]",A="[^"+c+g+R+i+t+b+"]",Q="\\ud83c[\\udffb-\\udfff]",X="(?:"+F+"|"+Q+")",u0="[^"+c+"]",m="(?:\\ud83c[\\udde6-\\uddff]){2}",C="[\\ud800-\\udbff][\\udc00-\\udfff]",x="["+b+"]",e0="\\u200d",d="(?:"+l+"|"+A+")",r0="(?:"+x+"|"+A+")",o="(?:"+p+"(?:d|ll|m|re|s|t|ve))?",n="(?:"+p+"(?:D|LL|M|RE|S|T|VE))?",O=X+"?",v="["+q+"]?",x0="(?:"+e0+"(?:"+[u0,m,C].join("|")+")"+v+O+")*",f0="\\d*(?:1st|2nd|3rd|(?![123])\\dth)(?=\\b|[A-Z_])",a0="\\d*(?:1ST|2ND|3RD|(?![123])\\dTH)(?=\\b|[a-z_])",d0=v+O+x0,o0="(?:"+[K,m,C].join("|")+")"+d0,n0=RegExp([x+"?"+l+"+"+o+"(?="+[a,x,"$"].join("|")+")",r0+"+"+n+"(?="+[a,x+d,"$"].join("|")+")",x+"?"+d+"+"+o,x+"+"+n,a0,f0,R,o0].join("|"),"g");function s0(u){return u.match(n0)||[]}function c0(u,e,r){return u=s(u),e=e,e===void 0?H(u)?s0(u):T(u):u.match(e)||[]}var i0="['’]",t0=RegExp(i0,"g");function b0(u){return function(e){return E(c0($(e).replace(t0,"")),u,"")}}var l0=b0(function(u,e,r){return e=e.toLowerCase(),u+(r?L(e):e)});export{l0 as c};
//# sourceMappingURL=camelCase-DAUzA25U.js.map
