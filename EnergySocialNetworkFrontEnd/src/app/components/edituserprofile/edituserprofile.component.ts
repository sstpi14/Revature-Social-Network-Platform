import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Profile } from 'src/app/models/profile';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-edituserprofile',
  templateUrl: './edituserprofile.component.html',
  styleUrls: ['./edituserprofile.component.css']
})
export class EdituserprofileComponent implements OnInit {
  id!: number;
  user!: string;

  username: string = "";
  password: string = "";
  firstname: string = "";
  lastname: string = "";
  email: string = "";
  
  updateMessageVariable : string = "";


  profile : Profile = {
    username: '',
    password: '',
    first_name: '',
    last_name: '',
    email: ''
  }
  constructor(private apiServ : ApiService, private router : Router, private route : ActivatedRoute) { }

  ngOnInit(): void {
    //this.getAllDisplays
    this.route.queryParams
    .subscribe(params => {
      this.id = params['id'];
    })
    this.getOneProfile();
  }

  getOneProfile(){
    this.apiServ.getOneProfileByProfileId(this.id).subscribe(response => {
      this.profile = response.data;
      console.log(this.profile);
    })
  }
  
  
  /* edit(){
    console.log(this.username, this.password, this.firstname, this.lastname, this.email)
  }
   */
    
  edit(){
    this.apiServ.updateProfile(this.profile).subscribe(response => { 
      if(response.success == false){
        this.updateMessageVariable = "Username or Email already taken."
      } else {
        this.router.navigate(["/user"])
      }
    })
    }
}
