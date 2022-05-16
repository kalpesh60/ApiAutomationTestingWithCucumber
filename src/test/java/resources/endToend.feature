Feature: End to end tests for Spotify Api
  Background: User generates token for Authorisation
  Scenario: the Authorized user can add tracks to his playlist.
    Given I have authorization details
    When I add a tracks
    Then The track is added

  Scenario: the Authorized user can create playlist
    Given I have current user playlist details
    When I am matching with playlist name
    Then The playlist name is matched