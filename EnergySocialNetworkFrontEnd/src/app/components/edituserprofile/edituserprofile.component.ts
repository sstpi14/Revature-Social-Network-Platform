import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
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
  
  constructor(private apiServ : ApiService, private router : Router) { }

  ngOnInit(): void {
  }

  edit(){
    console.log(this.username, this.password, this.firstname, this.lastname, this.email)
  }
}
