import{jsxs as L,jsx as t}from"react/jsx-runtime";import{u as R,a as b,$ as x,b as A,c as B,d as u,e as V,B as E,P as $,S as w,T as N,f as F,L as f,g as c,k as G,h as H,t as O}from"./main-C047jAp3.js";import{useState as d}from"react";import{u as _}from"./ConfirmDialog-d09hGFUk.js";import{V as j}from"./ViewHeader-Y2Z8ln-o.js";import{P as p}from"./constants-BclHbWtx.js";import"react-dom";const q=({obj:s,field:a})=>{const{realm:i}=u(),o=H(s,a);return t(f,{to:O({realm:i,providerId:s.providerId,id:s.id}),children:o},o)};function Z(){const{adminClient:s}=R(),{t:a}=b(),{addAlert:i,addError:o}=x(),g=A(),{providerId:y}=B(),[l,I]=d(0),v=()=>I(l+1),{realm:m,realmRepresentation:h}=u(),[D,P]=d(),{componentTypes:S}=V(),r=S?.[p]?.find(e=>e.id===y),C=async()=>{const e={parent:h?.id,type:p};return await s.components.find({...e})},[K,T]=_({titleKey:"itemDeleteConfirmTitle",messageKey:"itemDeleteConfirm",continueButtonLabel:"delete",continueButtonVariant:E.danger,onConfirm:async()=>{try{await s.components.del({id:D.id}),i(a("itemDeletedSuccess")),v()}catch(e){o("itemSaveError",e)}}});return L($,{variant:"light",className:"pf-v5-u-p-0",children:[t(T,{}),t(j,{titleKey:r.id,subKey:r.helpText,divider:!1}),t(w,{toolbarItem:t(N,{children:t(F,{component:e=>t(f,{...e,to:c({realm:m,providerId:r.id})}),children:a("createItem")})}),actionResolver:e=>[{title:a("delete"),onClick(){P(e.data),K()}}],searchPlaceholderKey:"searchItem",loader:C,columns:[...(r.metadata.displayFields||r.properties.slice(0,3).map(e=>e.name)).map((e,k)=>({name:`config.${e}[0]`,displayKey:r.properties.find(n=>n.name===e).label,cellRenderer:k===0?n=>t(q,{obj:n,field:`config.${e}`}):void 0}))],ariaLabelKey:"list",emptyState:t(G,{hasIcon:!0,message:a("noItems"),instructions:a("noItemsInstructions"),primaryActionText:a("createItem"),onPrimaryAction:()=>g(c({realm:m,providerId:r.id}))})},l)]})}export{Z as default};
//# sourceMappingURL=PageList-CMYdIP7r.js.map
