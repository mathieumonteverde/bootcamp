<div class="form-group row">
   <label class="col-sm-12">Pokemon types</label>
   <c:set var="i" value="1" scope="page" />
   <c:forEach items="${typesValues}" var ="typeValue">
      <select class="custom-select col-xs-12 col-sm-6 col-md-6 col-lg-6" name="pokemonType${i}" id="pokemonType${i}">
         <c:if test = "${typeValue eq 'Type '.concat(i)}"><option selected>${typeValue}</option></c:if>
         <c:if test = "${typeValue ne 'Type '.concat(i)}"><option>${'Type '.concat(i)}</option></c:if>
         <c:forEach items="${types}" var="type">
            <option value="${type.name}" <c:if test = "${typeValue eq type.name}">selected</c:if>>${type.name}</option>
         </c:forEach>
      </select>
      <c:set var="i" value="${i + 1}" scope="page"/>
   </c:forEach>
</div>