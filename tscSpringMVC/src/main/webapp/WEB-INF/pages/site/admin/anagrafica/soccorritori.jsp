<div id="rescuers">
	<h2>Participants</h2>
	Here are the participants:
	<div data-bind="template: { name: 'person-template', data: buyer }"></div>
	<div data-bind="template: { name: 'person-template', data: seller }"></div>
	 
	<script type="text/html" id="person-template">
    <h3 data-bind="text: name"></h3>
    <p>Credits: <span data-bind="text: credits"></span></p>
	</script>
</div>

 
<script type="text/javascript">
function MyViewModel() {
    this.buyer = { name: 'Franklin', credits: 250 };
    this.seller = { name: 'Mario', credits: 3000 };
}
ko.applyBindings(new MyViewModel(),document.getElementById('rescuers'));
</script>