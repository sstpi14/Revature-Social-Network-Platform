import { Component, OnInit } from '@angular/core';

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
  constructor() { }

  ngOnInit(): void {
  }

  edit(){
    console.log(this.username, this.password, this.firstname, this.lastname, this.email)
  }
}
