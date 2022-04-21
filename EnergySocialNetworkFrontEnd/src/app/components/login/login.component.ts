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
    first_name: '',
    last_name: '',
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

  image : string = "https://www.google.com/imgres?imgurl=https%3A%2F%2Fimages.newscientist.com%2Fwp-content%2Fuploads%2F2022%2F03%2F08165012%2FPRI_218356096.jpg&imgrefurl=https%3A%2F%2Fwww.newscientist.com%2Flastword%2Fmg25333771-200-if-energy-cannot-be-created-or-destroyed-where-does-it-come-from%2F&tbnid=PT_R-UyNlO41pM&vet=12ahUKEwiDjPynkaH3AhWVA50JHfDYD78QMygOegUIARDzAQ..i&docid=Q2NWMS2D2PrhKM&w=1200&h=800&q=energy&hl=en&ved=2ahUKEwiDjPynkaH3AhWVA50JHfDYD78QMygOegUIARDzAQ"

}
