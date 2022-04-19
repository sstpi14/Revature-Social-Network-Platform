import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { HomefeedComponent } from './components/homefeed/homefeed.component';
import { UserprofileComponent } from './components/userprofile/userprofile.component';
import { EdituserprofileComponent } from './components/edituserprofile/edituserprofile.component';
import { OtheruserprofileComponent } from './components/otheruserprofile/otheruserprofile.component';
import { DisplayblockComponent } from './components/otheruserprofile/displayblock/displayblock.component';
import{ HttpClientModule } from '@angular/common/http';
import { UserdisplayblockComponent } from './components/userprofile/userdisplayblock/userdisplayblock.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent, 
<<<<<<< HEAD
    RegisterComponent, HomefeedComponent, UserprofileComponent, EdituserprofileComponent, OtheruserprofileComponent, DisplayblockComponent, UserdisplayblockComponent
=======
    RegisterComponent, HomefeedComponent, UserprofileComponent, EdituserprofileComponent, OtheruserprofileComponent, 
>>>>>>> editprofile
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
