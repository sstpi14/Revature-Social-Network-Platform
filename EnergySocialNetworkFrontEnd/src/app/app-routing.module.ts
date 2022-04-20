import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EdituserprofileComponent } from './components/edituserprofile/edituserprofile.component';
import { HomefeedComponent } from './components/homefeed/homefeed.component';
import { LoginComponent } from './components/login/login.component';
import { OtheruserprofileComponent } from './components/otheruserprofile/otheruserprofile.component';
import { RegisterComponent } from './components/register/register.component';
import { UserprofileComponent } from './components/userprofile/userprofile.component';

const routes: Routes = [
  {path: "", component: LoginComponent},
  {path: "logout", component: LoginComponent},
  {path: "register", component: RegisterComponent},
  {path: "home", component: HomefeedComponent},
  {path: "user/:username", component: UserprofileComponent},
  {path: "otheruser", component: OtheruserprofileComponent},
  {path: "edit", component: EdituserprofileComponent},
  {path: "**", redirectTo: ""}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
