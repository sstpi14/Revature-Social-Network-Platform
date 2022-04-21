import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Profile } from 'src/app/models/profile';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-userprofile',
  templateUrl: './userprofile.component.html',
  styleUrls: ['./userprofile.component.css']
})
export class UserprofileComponent implements OnInit {

  isVisible : boolean = false;
  displays: Array<any> = [];
  profiles: Array<Profile> = [];
  isLiked:boolean = false;
  id!: number;
  user!: string;
  profileId!: number;

 

  profile : any = {
    profileId: 0,
    username: '',
    firstname: '',
    lastname: '',
    image: ''
  }

  constructor(private apiServ : ApiService, private router : Router, private route : ActivatedRoute) {
   }



  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.id = params['id'];
      this.user = params['user']
    })
    console.log();
    this.getAllDisplaysGivenProfileId();
    this.getOneProfileByProfileId();
    
  }
  getAllDisplaysGivenProfileId(){
    this.apiServ.getAllGivenProfileId(this.id).subscribe(response => {
      this.displays = response.data;
    })
  }
  getOneProfileByProfileId(){
    this.apiServ.getOneProfileByUsername(this.user).subscribe(response => {
      this.profile = response.data;
      this.profileId = response.data.profileId;
      console.log(this.profile);
      console.log(this.profile.profileId);
      console.log(this.id);
      if(this.id == this.profileId) {
        this.isVisible = true;
      }
    })
  }
  goToEdit(){
    this.router.navigate(["/edit"], { queryParams: { id: this.profile.profileId }});
    console.log(this.profile.profileId);
  }
  
  goToUser(e:any){
    this.router.navigate(["/user"], { queryParams: { user: e.target.innerText, id: this.profile.profileId }});
    console.log(e.target.innerText);
  }

}
