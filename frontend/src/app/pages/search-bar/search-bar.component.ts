import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { FormControl } from '@angular/forms';
import { debounceTime, distinctUntilChanged, filter } from 'rxjs';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.css'],
})
export class SearchBarComponent implements OnInit {
  @Output()
  searchValueChange = new EventEmitter<string>();
  tvShowName = new FormControl('');

  constructor(private router: Router, private route: ActivatedRoute) {}

  ngOnInit() {
    this.tvShowName.valueChanges
      .pipe(debounceTime(400), distinctUntilChanged())
      .subscribe((val) => {
        this.searchValueChange.emit(val || '');
        this.router.navigate([], {
          relativeTo: this.route,
          queryParams: { query: val },
        });
      });

    this.route.queryParamMap
      .pipe(filter((queryMap) => queryMap.has('query')))
      .subscribe((queryMap) => {
        this.tvShowName.setValue(queryMap.get('query'));
      });
  }
}
