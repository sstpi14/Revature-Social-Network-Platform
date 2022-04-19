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

}
