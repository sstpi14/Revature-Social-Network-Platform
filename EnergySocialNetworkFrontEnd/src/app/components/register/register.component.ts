import { HttpResponse, JsonpClientBackend } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { JsonResponse } from 'src/app/models/jsonresponse';
import { Profile } from 'src/app/models/profile';
import { ApiService } from 'src/app/services/api.service';
import { __values } from 'tslib';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  username: string = "";
  password: string = "";
  firstname: string = "";
  lastname: string = "";
  email: string = "";

  registerMessageVariable : string = "";

  profile : Profile = {
    username: this.username,
    password: this.password,
    first_name: this.firstname,
    last_name: this.lastname,
    email: this.email
  }

  constructor(private apiServ : ApiService, private router : Router) { }

  ngOnInit(): void {
    /* this.apiServ.getAllProfiles(); */
  }

  register(){
    this.profile = {
      username: this.username,
      password: this.password,
      first_name: this.firstname,
      last_name: this.lastname,
      email: this.email
    }
    this.apiServ.createProfile(this.profile).subscribe(response => { 
      if(response.success == false){
        this.registerMessageVariable = "Username or Email already taken."
      } else {
        this.router.navigate(["/login"])
      }
    })
    }

}
