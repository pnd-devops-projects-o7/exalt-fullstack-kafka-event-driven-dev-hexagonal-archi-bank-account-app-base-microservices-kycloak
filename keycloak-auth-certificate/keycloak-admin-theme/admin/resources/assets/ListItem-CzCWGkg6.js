import{aq as b,ar as a}from"./main-C047jAp3.js";import*as o from"react";const i={list:"pf-v5-c-list",listItem:"pf-v5-c-list__item",listItemIcon:"pf-v5-c-list__item-icon",modifiers:{iconLg:"pf-m-icon-lg",plain:"pf-m-plain",inline:"pf-m-inline",bordered:"pf-m-bordered"}};var m;(function(e){e.number="1",e.lowercaseLetter="a",e.uppercaseLetter="A",e.lowercaseRomanNumber="i",e.uppercaseRomanNumber="I"})(m||(m={}));var u;(function(e){e.inline="inline"})(u||(u={}));var c;(function(e){e.ol="ol",e.ul="ul"})(c||(c={}));const N=e=>{var{className:s="",children:n=null,variant:l=null,isBordered:f=!1,isPlain:t=!1,iconSize:r="default",type:L=m.number,ref:p=null,component:v=c.ul}=e,d=b(e,["className","children","variant","isBordered","isPlain","iconSize","type","ref","component"]);return v===c.ol?o.createElement("ol",Object.assign({ref:p,type:L},t&&{role:"list"},d,{className:a(i.list,l&&i.modifiers[l],f&&i.modifiers.bordered,t&&i.modifiers.plain,r&&r==="large"&&i.modifiers.iconLg,s)}),n):o.createElement("ul",Object.assign({ref:p},t&&{role:"list"},d,{className:a(i.list,l&&i.modifiers[l],f&&i.modifiers.bordered,t&&i.modifiers.plain,r&&r==="large"&&i.modifiers.iconLg,s)}),n)};N.displayName="List";const g=e=>{var{icon:s=null,children:n=null}=e,l=b(e,["icon","children"]);return o.createElement("li",Object.assign({className:a(s&&i.listItem)},l),s&&o.createElement("span",{className:a(i.listItemIcon)},s),n)};g.displayName="ListItem";export{N as L,u as a,g as b};
//# sourceMappingURL=ListItem-CzCWGkg6.js.map
