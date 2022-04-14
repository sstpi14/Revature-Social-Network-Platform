import { Component, OnInit } from '@angular/core';

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

  constructor() { }

  ngOnInit(): void {
  }

  register(){
    console.log(this.username, this.password, this.firstname, this.lastname, this.email)
  }

}
