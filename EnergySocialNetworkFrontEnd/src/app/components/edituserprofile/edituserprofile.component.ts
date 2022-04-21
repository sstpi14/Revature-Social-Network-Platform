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

  img: string = "";
  username: string = "";
  password: string = "";
  firstname: string = "";
  lastname: string = "";
  email: string = "";
  
  updateMessageVariable : string = "";


  profile : any = {
    username: '',
    password: '',
    first_name: '',
    last_name: '',
    email: '',
    profileId: 0
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
      this.img = response.data.img;
      //console.log(this.profile);
      //console.log(this.img);
    })
  }
  
  
  /* edit(){
    console.log(this.username, this.password, this.firstname, this.lastname, this.email)
  }
   */
    
  edit(){
    this.profile = {
      profileId: this.id,
      username: this.username,
      password: this.password,
      first_name: this.firstname,
      last_name: this.lastname,
      img: this.img,
      email: this.email
    }

    this.apiServ.updateProfile(this.profile).subscribe(response => { 
      if(response.success == false){
        this.updateMessageVariable = "Username or Email already taken."
      } else {
        this.goToUser(this.profile.username, this.profile.profileId);
        //console.log(this.profile);

      }
    })
    }

    goToUser(name:any, proid:any){
    this.router.navigate(["/user"], { queryParams: { user: name, id: proid }});

  }
}
