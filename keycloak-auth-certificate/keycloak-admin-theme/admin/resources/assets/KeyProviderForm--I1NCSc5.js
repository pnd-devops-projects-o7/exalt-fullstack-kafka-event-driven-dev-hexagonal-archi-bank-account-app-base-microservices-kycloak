import{jsxs as c,Fragment as C,jsx as a}from"react/jsx-runtime";import{a as y,b as F,P as E,eH as K,u as S,$ as T,e as H,eI as d,j as V,C as j,m as q,Q as u,A as w,f as v,p as f}from"./main-C047jAp3.js";import{D as O}from"./DynamicComponents-CCNvrlYx.js";import{F as k}from"./FormAccess-CEFqBG2E.js";import{V as x}from"./ViewHeader-Y2Z8ln-o.js";import{u as P}from"./useParams-6mfeCu18.js";import"react";import"react-dom";import"./ClientSelect-mboGti-R.js";import"./FileUpload-2t3HfN2b.js";import"./CodeEditor--AHMF_Ho.js";import"./copy-icon-D1BxihC2.js";import"./GroupPickerDialog-CRtxj-P-.js";import"./DataListItemRow-BUcm7LGX.js";import"./KeySelect-DvFB2tXI.js";import"./useToggle-K3Kx99tM.js";import"./MultiLineInput-lQlb7SN3.js";import"./AddRoleMappingModal-BqwO7G2x.js";import"./translationFormatter-CkqjPJdy.js";import"./useLocaleSort-O4LGWO8i.js";import"./ConfirmDialog-d09hGFUk.js";import"./filter-icon-BeqpAxqe.js";const D=({providerType:i,onClose:o})=>{const{adminClient:t}=S(),{t:r}=y(),{id:s}=P(),{addAlert:p,addError:b}=T(),h=H().componentTypes?.[d]??[],l=V({mode:"onChange"}),{handleSubmit:g,reset:I}=l,A=async e=>{e.config&&Object.entries(e.config).forEach(([n,m])=>e.config[n]=Array.isArray(m)?m:[m]);try{s?(await t.components.update({id:s},{...e,providerType:d}),p(r("saveProviderSuccess"),f.success)):(await t.components.create({...e,providerId:i,providerType:d}),p(r("saveProviderSuccess"),f.success),o?.())}catch(n){b("saveProviderError",n)}};return j(async()=>{if(s)return await t.components.findOne({id:s})},e=>{e&&I({...e})},[]),a(k,{isHorizontal:!0,role:"manage-realm",onSubmit:g(A),children:c(q,{...l,children:[s&&a(u,{name:"id",label:r("providerId"),labelIcon:r("providerIdHelp"),rules:{required:r("required")},readOnly:!0}),a(u,{name:"name",defaultValue:i,label:r("name"),labelIcon:r("keyProviderMapperNameHelp"),rules:{required:r("required")}}),a(O,{properties:h.find(e=>e.id===i)?.properties||[]}),c(w,{children:[a(v,{"data-testid":"add-provider-button",variant:"primary",type:"submit",children:r("save")}),a(v,{onClick:()=>o?.(),variant:"link",children:r("cancel")})]})]})})};function nr(){const{t:i}=y(),o=P(),t=F();return c(C,{children:[a(x,{titleKey:i("editProvider"),subKey:o.providerType}),a(E,{variant:"light",children:a(D,{...o,onClose:()=>t(K({realm:o.realm,tab:"providers"}))})})]})}export{D as KeyProviderForm,nr as default};
//# sourceMappingURL=KeyProviderForm--I1NCSc5.js.map
