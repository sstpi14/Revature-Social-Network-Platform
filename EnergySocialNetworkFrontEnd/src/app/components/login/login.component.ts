import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username: string = "";
  password: string = "";

  loginMessageVariable : string = "";

  constructor(private apiServ : ApiService, private router : Router) { }

  ngOnInit(): void {
  }

  login(){
    this.apiServ.getOneProfileByUsername(this.username).subscribe(response => {
      if (response.success == false) {
        this.loginMessageVariable = "Incorrect Credentials";
      } else {
        if (response.data.password === this.password) {
          this.router.navigate(["/home"]);
          this.apiServ.loginSession(response.data).subscribe(response1 => {
            console.log(response1);
          })
        } else {
          this.loginMessageVariable = "Incorrect Credentials";
        }
      }
    });
  }

  image : string = "https://www.google.com/imgres?imgurl=https%3A%2F%2Fimages.newscientist.com%2Fwp-content%2Fuploads%2F2022%2F03%2F08165012%2FPRI_218356096.jpg&imgrefurl=https%3A%2F%2Fwww.newscientist.com%2Flastword%2Fmg25333771-200-if-energy-cannot-be-created-or-destroyed-where-does-it-come-from%2F&tbnid=PT_R-UyNlO41pM&vet=12ahUKEwiDjPynkaH3AhWVA50JHfDYD78QMygOegUIARDzAQ..i&docid=Q2NWMS2D2PrhKM&w=1200&h=800&q=energy&hl=en&ved=2ahUKEwiDjPynkaH3AhWVA50JHfDYD78QMygOegUIARDzAQ"

}
