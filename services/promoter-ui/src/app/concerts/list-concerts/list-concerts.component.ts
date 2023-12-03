import {Component, OnInit} from '@angular/core';
import {ConcertHttpClientService} from "../http-client/concert-http-client.service";
import {SnackBarService} from "../../shared/snack-bar.service";
import {ConcertDto} from "../http-client/concert-dto.model";

@Component({
  selector: 'app-list-concert',
  templateUrl: './list-concerts.component.html',
  styleUrls: ['./list-concerts.component.scss']
})
export class ListConcertsComponent implements OnInit {

  public concerts: ConcertDto[] = [];
  displayedColumns: string[] = ['title', 'date', 'id'];

  constructor(private concertHttpClient: ConcertHttpClientService,
              private snackBar: SnackBarService) { }

  ngOnInit(): void {
    this.getConcerts();
  }

  private getConcerts() {
    this.concertHttpClient.getConcerts()
      .subscribe({
        next: (ConcertDtos: ConcertDto[]) => {
          this.concerts = ConcertDtos;
        },
        error: err => {
          this.snackBar.open('Error getting concerts: ' + JSON.stringify(err.error));
        }
      })
  }
}
