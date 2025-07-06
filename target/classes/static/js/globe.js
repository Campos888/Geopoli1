const Globe = window.Globe;
const myGlobe = Globe()
  .globeImageUrl('//unpkg.com/three-globe/example/img/earth-blue-marble.jpg')
  .polygonsData([])
  .polygonCapColor(() => 'rgba(0, 150, 255, 0.3)')
  .polygonSideColor(() => 'rgba(0, 100, 150, 0.15)')
  .polygonStrokeColor(() => '#333')
  .onPolygonClick(polygon => {
    const name = polygon.properties.CONTINENT || polygon.properties.name;
    alert(`You clicked: ${name}`);
    // TODO: Fetch or display countries from this continent
  });

myGlobe(document.getElementById('globeViz'));

// Load continents instead of countries
fetch('https://raw.githubusercontent.com/johan/world.geo.json/master/continents.geo.json')
  .then(res => res.json())
  .then(data => {
    myGlobe.polygonsData(data.features);
  });
