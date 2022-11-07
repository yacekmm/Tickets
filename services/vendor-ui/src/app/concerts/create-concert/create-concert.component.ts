import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {CreateConcertResultDto} from '../http-client/create-concert-result-dto.model';
import {ConcertHttpClientService} from '../http-client/concert-http-client.service';
import {SnackBarService} from '../../shared/snack-bar.service';
import * as moment from 'moment';

@Component({
  selector: 'app-create-concert',
  templateUrl: './create-concert.component.html',
  styleUrls: ['./create-concert.component.scss']
})
export class CreateConcertComponent implements OnInit {

  modelCreateConcert: any;
  errorMessage: string = '';
  requestInProgress: boolean = false;

  @Output() concertCreated = new EventEmitter<CreateConcertResultDto>();

  constructor(private concertHttpClient: ConcertHttpClientService,
              private snackBar: SnackBarService) {
  }

  ngOnInit() {
    this.initForm();
  }

  private initForm(): void {
    this.modelCreateConcert = {};
    this.errorMessage = '';
    this.requestInProgress = false;
  }

  createConcert() {
    let selectedMoment: moment.Moment = moment(this.modelCreateConcert.date);
    this.requestInProgress = true;
    this.concertHttpClient.createConcert(this.modelCreateConcert.title, selectedMoment.format("YYYY-MM-DD"))
      .subscribe({
        next: (createConcertResultDto: CreateConcertResultDto) => {
          this.initForm();
          this.requestInProgress = false;
          this.concertCreated.emit(createConcertResultDto);
          this.snackBar.open('Concert created with id: ' + createConcertResultDto.id)
        },
        error: err => {
          this.errorMessage = 'Error creating concert: ' + JSON.stringify(err.error);
          this.requestInProgress = false;
        }
      });
  }
}
