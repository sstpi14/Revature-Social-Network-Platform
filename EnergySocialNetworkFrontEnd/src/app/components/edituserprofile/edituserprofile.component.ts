import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Profile } from 'src/app/models/profile';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-edituserprofile',
  templateUrl: './edituserprofile.component.html',
  styleUrls: ['./edituserprofile.component.css']
})
export class EdituserprofileComponent implements OnInit {

  username: string = "";
  password: string = "";
  firstname: string = "";
  lastname: string = "";
  email: string = "";
  
  updateMessageVariable : string = "";


  profile : Profile = {
    username: this.username,
    password: this.password,
    first_name: this.firstname,
    last_name: this.lastname,
    email: this.email
  }
  constructor(private apiServ : ApiService, private router : Router) { }

  ngOnInit(): void {
  }
  
  /* edit(){
    console.log(this.username, this.password, this.firstname, this.lastname, this.email)
  }
   */
  edit(){
    this.profile = {
      username: this.username,
      password: this.password,
      first_name: this.firstname,
      last_name: this.lastname,
      email: this.email
    }
    
    this.apiServ.updateProfile(this.profile).subscribe(response => { 
      if(response.success == false){
        this.updateMessageVariable = "Username or Email already taken."
      } else {
        this.router.navigate(["/user"])
      }
    })
    }
}
