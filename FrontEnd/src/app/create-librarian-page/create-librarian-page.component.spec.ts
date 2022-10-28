import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateLibrarianPageComponent } from './create-librarian-page.component';

describe('CreateLibrarianPageComponent', () => {
  let component: CreateLibrarianPageComponent;
  let fixture: ComponentFixture<CreateLibrarianPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateLibrarianPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateLibrarianPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
