import{jsxs as v,jsx as o}from"react/jsx-runtime";import{Children as K,isValidElement as g}from"react";import{dy as x,c as P,e as R,bC as I,a as D,e2 as E,e3 as C,bD as F,e4 as p}from"./main-C047jAp3.js";import{P as j}from"./PageHandler-BsZt2gxJ.js";import{T as c}from"./constants-BclHbWtx.js";import{T as A,d as H,a as L}from"./Tabs-Dt-AxIu8.js";const k=({children:a,defaultLocation:t,...n})=>{const{pathname:s}=x(),i=P(),{componentTypes:l}=R(),h=l?.[c]||[],d=I(),{t:u}=D(),r=h.filter(e=>E({path:e.metadata.path},s)).map(e=>({...e,pathname:C(e.metadata.path,{...i,...e.metadata.params})})),f=r.map(e=>e.pathname),m=K.toArray(a).filter(e=>g(e)).map(e=>e.props.eventKey.toString()),y=[...m,...f].find(e=>e===decodeURI(s)),T=m.filter(e=>s.includes(e)).sort((e,b)=>e.length-b.length).pop();return v(A,{activeKey:y??T??t?.pathname??s,component:H.nav,inset:{default:"insetNone",xl:"insetLg","2xl":"inset2xl"},...n,children:[a,d(F.DeclarativeUI)&&r.map(e=>o(M,{eventKey:e.pathname,title:u(e.id),children:o(j,{page:e,providerType:c})},e.id))]})},M=({children:a,...t})=>{const n=p(t.eventKey);return o(L,{href:n,...t,children:a})},q=a=>({eventKey:a.pathname??"",href:p(a)});export{k as R,q as u};
//# sourceMappingURL=RoutableTabs-BpMBzAU0.js.map
