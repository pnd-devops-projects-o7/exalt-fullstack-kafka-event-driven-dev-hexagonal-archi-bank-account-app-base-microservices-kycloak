import{jsx as a,jsxs as u,Fragment as k}from"react/jsx-runtime";import{u as I,b_ as R,d as se,C as T,f as S,L as ne,fi as oe,eY as le,cU as ce,cj as x,fo as de,a as M,W as ue,c7 as me,$ as pe,e1 as fe,b as be,m as he,J as b,a0 as U,K as v,fp as Ae,Q as y,eR as ye,X as ge,q as ke,N as ve,cd as Ie,ce as j,U as Le,V as Oe,a4 as qe,p as V,eu as we}from"./main-C047jAp3.js";import{useState as h,useEffect as Ge}from"react";import{D as Ce}from"./SwitchControl-87mhceN2.js";import{F as Fe,a as xe}from"./FormAccess-CEFqBG2E.js";import{G as Ue}from"./GroupPickerDialog-CRtxj-P-.js";import{u as je}from"./useFormatDate-Bn-bRoLD.js";const Ve=({user:r})=>{const{adminClient:n}=I(),e=R(),{realm:o}=se(),[s,l]=h();return T(()=>e.hasAccess("view-realm")?n.components.findOne({id:r.federationLink}):n.userStorageProvider.name({id:r.federationLink}),l,[]),s?e.hasAccess("view-realm")?a(S,{variant:"link",component:f=>a(ne,{...f,to:oe({id:s.id,providerId:s.providerId,realm:o})}),children:s.name}):a("span",{children:s.name}):null};function Re(r){const n={};Object.entries(r.attributes||{}).forEach(([o,s])=>n[le(o)]=s);const e=ce(r.unmanagedAttributes);return{...r,attributes:n,unmanagedAttributes:e}}function Be(r){const n=r.username?.trim(),e=Array.isArray(r.attributes)?x(r.attributes):Object.fromEntries(Object.entries(r.attributes||{}).map(([s,l])=>[de(s),l])),o=Array.isArray(r.unmanagedAttributes)?x(r.unmanagedAttributes):r.unmanagedAttributes;for(const s in o)if(e&&Object.hasOwn(e,s))throw Error(`Attribute ${s} is a managed attribute and is already available from the user details.`);return{...r,username:n,attributes:{...o,...e},unmanagedAttributes:void 0}}function $e(r={},n={}){return Object.fromEntries(Object.entries(r).filter(([e])=>!Object.hasOwn(n,e)))}const Te=({name:r,label:n,help:e})=>{const{adminClient:o}=I(),{t:s}=M(),[l,f]=h([]);return T(()=>o.authenticationManagement.getRequiredActions(),d=>{const m=d.filter(A=>A.enabled);f(m)},[]),a(ue,{name:r,label:s(n),labelIcon:s(e),controller:{defaultValue:[]},isScrollable:!0,maxMenuHeight:"375px",variant:me.typeaheadMulti,chipGroupProps:{numChips:3},placeholderText:s("requiredActionPlaceholder"),menuAppendTo:"parent",options:l.map(({alias:d,name:m})=>({key:d,value:m||d}))})},We=({form:r,realm:n,user:e,bruteForce:{isBruteForceProtected:o,isLocked:s}={isBruteForceProtected:!1,isLocked:!1},userProfileMetadata:l,save:f,refresh:d,onGroupsUpdate:m})=>{const{adminClient:A}=I(),{t}=M(),E=je(),{addAlert:L,addError:O}=pe(),{hasAccess:q}=R(),D=q("manage-users"),H=q("view-realm"),{whoAmI:N}=fe(),P=N.getLocale(),{handleSubmit:B,setValue:w,control:$,reset:W,formState:z}=r,{errors:K}=z,[p,G]=h([]),[C,g]=h(!1),[F,Q]=h(s),_=be();Ge(()=>{w("requiredActions",e?.requiredActions||[])},[e,w]);const J=async()=>{try{await A.users.update({id:e.id},{enabled:!0}),L(t("unlockSuccess"),V.success),d&&d()}catch(i){O("unlockError",i)}},X=i=>{G(p.filter(c=>c.name!==i)),m?.(p)},Y=async i=>{G([...p,...i]),m?.([...p,...i])},Z=async i=>{i.forEach(async re=>{try{await A.users.addToGroup({id:e.id,groupId:re.id}),L(t("addedGroupMembership"),V.success)}catch(ie){O("addedGroupMembershipError",ie)}})},ee=()=>{g(!C)},te=()=>{e?.id?W(Re(e)):_(we({realm:n.realm}))},ae=()=>e?.userProfileMetadata?.attributes&&!e?.userProfileMetadata?.attributes?.map(i=>i.readOnly).reduce((i,c)=>i&&c,!0);return u(Fe,{isHorizontal:!0,onSubmit:B(f),role:"query-users",fineGrainedAccess:e?.access?.manage,className:"pf-v5-u-mt-lg",children:[u(he,{...r,children:[C&&a(Ue,{type:"selectMany",text:{title:"selectGroups",ok:"join"},canBrowse:D,onConfirm:i=>{e?.id?Z(i||[]):Y(i||[]),g(!1)},onClose:()=>g(!1),filterGroups:p}),e?.id&&u(k,{children:[a(b,{label:t("id"),fieldId:"kc-id",isRequired:!0,children:a(U,{id:e.id,"aria-label":t("userID"),value:e.id,readOnly:!0})}),a(b,{label:t("createdAt"),fieldId:"kc-created-at",isRequired:!0,children:a(U,{value:E(new Date(e.createdTimestamp)),id:"kc-created-at",readOnly:!0})})]}),a(Te,{name:"requiredActions",label:"requiredUserActions",help:"requiredUserActionsHelp"}),e?.federationLink&&H&&a(b,{label:t("federationLink"),labelIcon:a(v,{helpText:t("federationLinkHelp"),fieldLabelId:"federationLink"}),children:a(Ve,{user:e})}),l?u(k,{children:[a(Ce,{name:"emailVerified",label:t("emailVerified"),labelIcon:t("emailVerifiedHelp")}),a(Ae,{form:r,userProfileMetadata:l,hideReadOnly:!e,supportedLocales:n.supportedLocales||[],currentLocale:P,t:(i,c)=>t(i,c)})]}):u(k,{children:[!n.registrationEmailAsUsername&&a(y,{name:"username",label:t("username"),readOnly:!!e?.id&&!n.editUsernameAllowed&&n.editUsernameAllowed!==void 0,rules:{required:t("required")}}),a(y,{name:"email",label:t("email"),type:"email",rules:{pattern:{value:ye,message:t("emailInvalid")}}}),a(ge,{name:"emailVerified",label:t("emailVerified"),labelIcon:t("emailVerifiedHelp"),labelOn:t("yes"),labelOff:t("no")}),a(y,{name:"firstName",label:t("firstName")}),a(y,{name:"lastName",label:t("lastName")})]}),o&&a(b,{label:t("temporaryLocked"),fieldId:"temporaryLocked",labelIcon:a(v,{helpText:t("temporaryLockedHelp"),fieldLabelId:"temporaryLocked"}),children:a(ke,{"data-testid":"user-locked-switch",id:"temporaryLocked",onChange:(i,c)=>{J(),Q(c)},isChecked:F,isDisabled:!F,label:t("on"),labelOff:t("off")})}),!e?.id&&u(b,{label:t("groups"),fieldId:"kc-groups",labelIcon:a(v,{helpText:t("groupsHelp"),fieldLabelId:"groups"}),children:[a(ve,{name:"groups",defaultValue:[],control:$,render:()=>u(Ie,{children:[a(j,{children:a(Le,{categoryName:" ",children:p.map(i=>a(Oe,{onClick:()=>X(i.name),children:i.path},i.id))})}),a(j,{children:a(S,{id:"kc-join-groups-button",onClick:ee,variant:"secondary","data-testid":"join-groups-button",children:t("joinGroups")})})]})}),K.requiredActions&&a(qe,{message:t("required")})]})]}),a(xe,{name:"user-creation",saveText:e?.id?t("save"):t("create"),reset:te,resetText:e?.id?t("revert"):t("cancel"),isDisabled:ae(),isSubmit:!0})]})};export{Ve as F,Te as R,We as U,Re as a,$e as f,Be as t};
//# sourceMappingURL=UserForm--6_Ea0DI.js.map
