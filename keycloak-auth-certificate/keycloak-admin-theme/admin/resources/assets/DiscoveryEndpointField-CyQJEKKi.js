import{jsxs as F,Fragment as g,jsx as d}from"react/jsx-runtime";import{useState as y,useMemo as I}from"react";import{u as x,a as C,Y as T,J as k,K as H,q as S,Q as j,dF as q}from"./main-C047jAp3.js";const f=(r,t,i={})=>{if(!Number.isFinite(t))throw new TypeError("Expected `wait` to be a finite number");let l,e,a=[];return function(...u){return new Promise(s=>{const n=i.before&&!e;clearTimeout(e),e=setTimeout(()=>{e=null;const p=i.before?l:r.apply(this,u);for(s of a)s(p);a=[]},t),n?(l=r.apply(this,u),s(l)):a.push(s)})}};f.promise=r=>{let t;return async function(...i){if(t)return t;try{return t=r.apply(this,i),await t}finally{t=void 0}}};const O=({id:r,fileUpload:t,children:i})=>{const{adminClient:l}=x(),{t:e}=C(),{setValue:a,clearErrors:u,formState:{errors:s}}=T(),[n,p]=y(!0),[v,m]=y(!1),[E,b]=y(),h=c=>{Object.keys(c).map(o=>a(`config.${o}`,c[o]))},D=async c=>{m(!0);try{const o=await l.identityProviders.importFromUrl({providerId:r,fromUrl:c});h(o),b(o)}catch(o){return o.message}finally{m(!1)}},w=I(()=>f(D,1e3),[]);return F(g,{children:[d(k,{label:e(r==="oidc"?"useDiscoveryEndpoint":"useEntityDescriptor"),fieldId:"kc-discovery-endpoint",labelIcon:d(H,{helpText:e(r==="oidc"?"useDiscoveryEndpointHelp":"useEntityDescriptorHelp"),fieldLabelId:"discoveryEndpoint"}),children:d(S,{id:"kc-discovery-endpoint-switch",label:e("on"),labelOff:e("off"),isChecked:n,onChange:(c,o)=>{u("discoveryError"),p(o)},"aria-label":e(r==="oidc"?"useDiscoveryEndpoint":"useEntityDescriptor")})}),n&&d(j,{name:"discoveryEndpoint",label:e(r==="oidc"?"discoveryEndpoint":"samlEntityDescriptor"),labelIcon:e(r==="oidc"?"discoveryEndpointHelp":"samlEntityDescriptorHelp"),type:"url",placeholder:r==="oidc"?"https://hostname/realms/master/.well-known/openid-configuration":"",validated:s.discoveryError||s.discoveryEndpoint?"error":E?"success":"default",customIcon:v?d(q,{isInline:!0}):void 0,rules:{required:e("required"),validate:c=>w(c)}}),!n&&t,n&&!s.discoveryError&&i(!0),!n&&i(!1)]})};export{O as D};
//# sourceMappingURL=DiscoveryEndpointField-CyQJEKKi.js.map
