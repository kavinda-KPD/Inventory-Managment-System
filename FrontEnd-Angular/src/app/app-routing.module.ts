import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {SignupComponent} from "./signup/signup.component";
import {InventoryHomePageComponent} from "./inventory-home-page/inventory-home-page.component";
import {SearchPageComponent} from "./search-page/search-page.component";

const routes: Routes = [
  {path:'', component:LoginComponent,pathMatch:"full"},
  {path: 'login', component:LoginComponent},
  {path: 'signup', component:SignupComponent},
  {path: 'home', component:InventoryHomePageComponent},
  {path: 'search', component:SearchPageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
