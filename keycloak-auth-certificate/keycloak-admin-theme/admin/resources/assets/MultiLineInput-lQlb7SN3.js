import{jsx as l,jsxs as d}from"react/jsx-runtime";import{useMemo as T,useEffect as G,Fragment as $}from"react";import{a as q,Y as D,I as E,cd as L,ce as f,a0 as P,f as m,B as v,a3 as S,ab as W}from"./main-C047jAp3.js";function I(a){return typeof a=="string"?a.split("##"):[a||""]}function g(a){return a.join("##")}const z=({name:a,addButtonLabel:y,isDisabled:i=!1,defaultValue:o,stringify:u=!1,isRequired:A=!1,id:C,...M})=>{const{t:n}=q(),{register:p,setValue:V,control:b}=D(),r=E({name:a,control:b,defaultValue:o||""}),s=T(()=>{let t=u?I(Array.isArray(r)&&r.length===1?r[0]:r):Array.isArray(r)?r:[r];return(!Array.isArray(t)||t.length===0)&&(t=(u?I(o):o)||[""]),t},[r]),k=t=>{c([...s.slice(0,t),...s.slice(t+1)])},j=()=>{c([...s,""])},B=(t,e)=>{c([...s.slice(0,t),e,...s.slice(t+1)])},c=t=>{const e=t.flatMap(h=>h);V(a,u?g(e):e,{shouldDirty:!0,shouldValidate:!0})};return G(()=>{p(a,{validate:t=>A&&g(t||[]).length===0?n("required"):void 0})},[p]),l("div",{id:C,children:s.map((t,e)=>d($,{children:[d(L,{children:[l(f,{isFill:!0,children:l(P,{"data-testid":a+e,onChange:(h,F)=>B(e,F),name:`${a}.${e}.value`,value:t,isDisabled:i,...M})}),l(f,{children:l(m,{"data-testid":"remove"+e,variant:v.link,onClick:()=>k(e),tabIndex:-1,"aria-label":n("remove"),isDisabled:s.length===1||i,children:l(S,{})})})]}),e===s.length-1&&d(m,{variant:v.link,onClick:j,tabIndex:-1,"aria-label":n("add"),"data-testid":"addValue",isDisabled:!t||i,children:[l(W,{})," ",n(y||"add")]})]},e))})};export{z as M};
//# sourceMappingURL=MultiLineInput-lQlb7SN3.js.map
