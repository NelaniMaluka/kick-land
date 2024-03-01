import React, { useState } from "react";
import PlacesAutocomplete, {
  geocodeByAddress,
  getLatLng,
} from "react-places-autocomplete";
import "./Profile.css";

function LocationSearchInput({ onAddressSelect, initialAddress }) {
  const [address, setAddress] = useState(initialAddress);
  const [isValidAddress, setIsValidAddress] = useState(true);

  const handleSelect = async (value) => {
    try {
      const result = await geocodeByAddress(value);

      if (result.length > 0) {
        const addressComponents = result[0].address_components;
        const isStreetAddress = addressComponents.some((component) =>
          component.types.includes("route")
        );

        if (isStreetAddress) {
          const formattedAddress = result[0].formatted_address;
          setAddress(formattedAddress);
          setIsValidAddress(true);

          // You can also get the latitude and longitude if needed
          const { lat, lng } = await getLatLng(result[0]);
          console.log("Latitude and Longitude:", lat, lng);

          // Pass the selected address back to the parent component
          onAddressSelect(formattedAddress);
        } else {
          setIsValidAddress(false);
        }
      } else {
        setIsValidAddress(false);
      }
    } catch (error) {
      console.error("Error validating address:", error);
      setIsValidAddress(false);
    }
  };

  const searchOptions = {
    componentRestrictions: { country: "ZA" },
  };

  return (
    <div>
      <PlacesAutocomplete
        placeholder={onAddressSelect}
        value={address}
        onChange={setAddress}
        onSelect={handleSelect}
        searchOptions={searchOptions}
      >
        {({ getInputProps, suggestions, getSuggestionItemProps, loading }) => (
          <div>
            <input
              {...getInputProps({
                placeholder: "e.g 41 bieker road",
                className: "messageField",
              })}
            />
            <div className="autocomplete-dropdown-container">
              {loading && <div>Loading...</div>}
              {suggestions.map((suggestion) => {
                const className = suggestion.active
                  ? "suggestion-item--active"
                  : "suggestion-item";
                const style = suggestion.active
                  ? { backgroundColor: "#fafafa", cursor: "pointer" }
                  : { backgroundColor: "#ffffff", cursor: "pointer" };
                return (
                  <div
                    {...getSuggestionItemProps(suggestion, {
                      className,
                      style,
                    })}
                  >
                    <span>{suggestion.description}</span>
                  </div>
                );
              })}
            </div>
          </div>
        )}
      </PlacesAutocomplete>
      {!isValidAddress && (
        <div style={{ color: "red" }}>
          Invalid address. Please enter a valid street address.
        </div>
      )}
    </div>
  );
}

export default LocationSearchInput;
