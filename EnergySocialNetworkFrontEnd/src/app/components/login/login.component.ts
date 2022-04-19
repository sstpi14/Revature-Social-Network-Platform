import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
/* import { url } from 'inspector'; */
import { Profiled } from 'src/app/models/dprofile';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username: string = "";
  password: string = "";

  profile : Profiled = {
    profileId: 0,
    username: '',
    firstname: '',
    lastname: '',
    image: ''
  }

  loginMessageVariable : string = "";

  constructor(private apiServ : ApiService, private router : Router, private route : ActivatedRoute) { }

  ngOnInit(): void {
  }

  login(){
    this.apiServ.getOneProfileByUsername(this.username).subscribe(response => {
      if (response.success == false) {
        this.loginMessageVariable = "Incorrect Credentials";
      } else {
        if (response.data.password === this.password) {
          
          this.apiServ.loginSession(response.data).subscribe(response1 => {
            this.profile.profileId = response.data.profileId;
            /* console.log(response1); */
            this.router.navigate(["/home"], { queryParams: {id: this.profile.profileId }});
          })
        } else {
          this.loginMessageVariable = "Incorrect Credentials";
        }
      }
    });
  }

/*   queryString : string = window.location.search;
  urlParams = new URLSearchParams(this.queryString);
  profileId = this.urlParams.get('id')
  console.log(profileId) */

}
